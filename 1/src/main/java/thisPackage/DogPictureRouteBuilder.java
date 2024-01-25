package thisPackage;

import org.apache.camel.builder.RouteBuilder;

public class DogPictureRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        getContext().setStreamCaching(true);
        from("timer:trigger?repeatCount=1") // Trigger the route only once
        .setHeader("Accept", constant("application/json"))
        .to("https://dog.ceo/api/breeds/image/random") // Send GET request to the API
        .log("Received Dog Image URL: ${body}") // Log the received message 
        .convertBodyTo(String.class) 
        .to("file:1/target/dog-images?fileName=random-dog.txt") 
        .log("Dog image URL written to target/dog-images/random-dog.json");
    
    }
}

