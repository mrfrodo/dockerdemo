package no.frodo.dd.controller;

import no.frodo.dd.domain.*;
import no.frodo.dd.services.CustomerService;
import no.frodo.dd.services.PoemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("p")
public class PoemController {

    private static final Logger logger = LoggerFactory.getLogger(PoemController.class);

    @Autowired
    PoemService poemService;

    @PostMapping
    PoemResponseDTO countWords(@RequestBody PoemRequestDTO poemRequestDTO) {
        logger.info("_cc_ countWords");
        return poemService.countWords(poemRequestDTO);
    }
}
