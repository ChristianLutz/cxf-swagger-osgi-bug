JAX-RS Swagger2Feature OSGI Issue
=================

This example is based on the code from 
https://github.com/apache/cxf/tree/master/distribution/src/main/release/samples/jax_rs/description_swagger2_osgi

How to reproduce the problem:
  mvn install
  bin/karaf
  
  on karaf@root()>
  feature:repo-add cxf 3.1.6
  feature:install cxf-rs-description-swagger2
  install mvn:com.fasterxml.jackson.jaxrs/jackson-jaxrs-base/2.6.5
  install mvn:com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider/2.6.5
  install -s mvn:de.kreeloo/cxf-swagger2-osgi-api/1.0.0
  install -s mvn:de.kreeloo/cxf-swagger2-osgi-impl/1.0.0
  
  
  Now open your web browser and type: 
  http://localhost:8181/cxf/swaggerSample/swagger.json
  And all you see is the swagger header.
  
  I guess the problem is the ClasspathHelper.class from org.reflections it looks like that this one is not able to access the osgi component. 
  
  The behavior is similar to this error description:
  http://cxf.547215.n5.nabble.com/Swagger2Feature-via-blueprint-config-does-not-produce-the-expected-results-td5761841.html


