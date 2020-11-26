package no.frodo.dd.services;

import no.frodo.dd.domain.*;
import no.frodo.dd.exception.DDInternalException;
import no.frodo.dd.exception.IllegalPoemException;
import no.frodo.dd.exception.NoDataFoundException;
import no.frodo.dd.repository.CustomerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PoemService {

    private static final Logger logger = LoggerFactory.getLogger(PoemService.class);

    @Autowired
    ModelMapper modelMapper;

    public PoemResponseDTO countWords(PoemRequestDTO customerRequestDTO) {

        validateRequest(customerRequestDTO);
        String poem = customerRequestDTO.getPoem();
        String[] words = poem.split(" ");

        return PoemResponseDTO.builder().words(words.length).build();
    }

    protected void validateRequest(PoemRequestDTO customerRequestDTO) {

        if (customerRequestDTO.getPoem().isEmpty()) {
            throw new IllegalPoemException();
        }

        logger.info("validate poem {}", customerRequestDTO.getPoem());

        String legalCharacters = "abcdefghijklmnopqrstuvwqyz";
        char[] legalCharactersArray = legalCharacters.toCharArray();

        String poem = customerRequestDTO.getPoem();
        char[] poemArray = poem.toCharArray();

       int length = poemArray.length;
       for (int i=0 ; i<length ; i++) {
           System.out.print(poemArray[i]);System.out.print(" ");
       }
        System.out.println("done");

    }


}
