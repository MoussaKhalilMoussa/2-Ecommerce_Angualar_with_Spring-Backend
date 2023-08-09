package com.example.ecommerce.Config;

import com.example.ecommerce.Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;
    private EntityManager entityManager;
    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // we are making them to read only (get)
        // disable HTTP methods for Product: PUT, POST, DELETE
        disableMethods(Product.class ,config, theUnSupportedActions);
        // disable HTTP methods for ProductCategory: PUT, POST, DELETE
        disableMethods(ProductCategory.class ,config, theUnSupportedActions);
        // disable HTTP methods for Country: PUT, POST, DELETE
        disableMethods(Country.class ,config, theUnSupportedActions);
        // disable HTTP methods for State: PUT, POST, DELETE
        disableMethods(State.class ,config, theUnSupportedActions);
        // disable HTTP methods for Order: PUT, POST, DELETE
        disableMethods(Order.class ,config, theUnSupportedActions);
        // call an internal helper method
        exposeIds(config);

        // configure cors mapping
        // did it but not working properly get errors
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }
    private static void disableMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnSupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity

        // -get a list of all entity classes form the entity manger
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - get the entity types for the entities
        for(EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        // -expose the entity ids for the array of the entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}
