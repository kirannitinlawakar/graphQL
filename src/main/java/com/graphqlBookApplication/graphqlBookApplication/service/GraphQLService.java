package com.graphqlBookApplication.graphqlBookApplication.service;

import com.graphqlBookApplication.graphqlBookApplication.AllBooksDataFetcher;
import com.graphqlBookApplication.graphqlBookApplication.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:books.graphqls")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    //load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        //get schema file
        File schemaFile = resource.getFile();
        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
        graphQL= GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",typeWiring-> typeWiring
                                                    .dataFetcher("allBooks",allBooksDataFetcher)
                                                    .dataFetcher("book",bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
}
