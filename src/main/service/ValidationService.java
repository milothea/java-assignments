package main.service;

import main.exceptions.InvalidArgumentException;

public interface ValidationService {
    void validateSector(char sector) throws InvalidArgumentException;

    void validateVenueName(String venueName) throws InvalidArgumentException;

    void validateEventCode(int eventCode) throws InvalidArgumentException;
}
