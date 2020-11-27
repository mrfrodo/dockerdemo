package no.frodo.dd.service;

import no.frodo.dd.domain.*;
import no.frodo.dd.exception.IllegalPoemException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
