package eddy.springkafka.controller;



import eddy.springkafka.service.BookerService;
import eddy.springkafka.vo.BookerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/booker/v1")
public class BookerController {

    private final BookerService bookerService;

    public BookerController(BookerService bookerService) {
        this.bookerService = bookerService;
    }

    @PostMapping(value = "/create")
    public String create(@RequestBody BookerVo bookerVo) {
        bookerService.create(bookerVo);
        return "create success";
    }
}
