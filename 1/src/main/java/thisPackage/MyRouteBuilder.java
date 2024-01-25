package thisPackage;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("file:1/src/data?noop=true")
            .choice()
                // Check if the file is JSON
                .when(header("CamelFileName").endsWith(".json"))
                    .unmarshal().json(JsonLibrary.Jackson)
                    .choice()
                        .when().jsonpath("$.person[?(@.city == 'London')]")
                            .log("UK message JSON")
                            .setHeader("CamelFileName", simple("uk.txt"))
                            .setBody(constant("foo"))
                            .to("file:1/target/messages/uk")
                        .when().jsonpath("$.person[?(@.city == 'Karlsruhe')]")
                            .log("Karlsruhe message JSON")
                            .to("file:1/target/messages/de")
                        .otherwise()
                            .log("Other message JSON")
                            .to("file:1/target/messages/others")
                    .endChoice()
                // Check if the file is XML
                .when(header("CamelFileName").endsWith(".xml"))
                    .choice()
                        .when(xpath("/person/city = 'London'"))
                            .log("UK message XML")
                            .setHeader("CamelFileName", simple("uk.txt"))
                            .setBody(constant("foo"))
                            .to("file:1/target/messages/uk")
                        .when(xpath("/person/city = 'Karlsruhe'"))
                            .log("Karlsruhe message XML")
                            .to("file:1/target/messages/de")
                        .otherwise()
                            .log("Other message XML")
                            .to("file:1/target/messages/others")
                    .endChoice()
                .endChoice();
    }
}
