package sample.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    ComplexClient complexClient;

    @RequestMapping("/country")
    public Country geCountry() {
        return complexClient.retrieveCountry();
    }

    @RequestMapping("/overload500")
    public void overload500() {
        complexClient.overload500();
    }

    @RequestMapping("/overload1000")
    public void overload1000() {
        complexClient.overload1000();
    }

    @RequestMapping("/overload2500")
    public void overload2500() {
        complexClient.overload2500();
    }

    @RequestMapping("/overload5000")
    public void overload5000() {
        complexClient.overload5000();
    }

    @RequestMapping("/overload10000")
    public void overload10000() {
        complexClient.overload10000();
    }

    @RequestMapping("/overload50000")
    public void overload50000() {
        complexClient.overload50000();
    }
}


