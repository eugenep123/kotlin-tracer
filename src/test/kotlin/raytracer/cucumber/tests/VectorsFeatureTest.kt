package raytracer.cucumber.tests

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features/vectors.feature"],
    tags = "not @Wip",
    glue = ["classpath:raytracer.cucumber.glue.vectors", "classpath:raytracer.cucumber.glue.shared"],
    plugin = ["pretty", "html:target/cucumber/html"]
)
class VectorsFeatureTest
