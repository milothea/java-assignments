package main.service;

import main.constants.Sectors;
import main.exceptions.InvalidArgumentException;

import java.util.Arrays;

public class ValidationServiceImpl implements ValidationService {
    public ValidationServiceImpl() {}

    public void validateSector (char sector) throws InvalidArgumentException {
        char[] validSectors = Sectors.values();

        if (Arrays.binarySearch(validSectors, sector) == -1) {
            throw new InvalidArgumentException("Invalid stadium sector: " + sector +
                    ". Please provide a valid sector from the list " + Arrays.toString(Sectors.values()));
        }
    }

    public void validateVenueName(String venueName) throws InvalidArgumentException {
        if (venueName.length() > 10) {
            throw new InvalidArgumentException("Venue name '" + venueName +
                    "' is too long. Please provide a name with 10 characters or less.");
        }
    }

    public void validateEventCode(int eventCode) throws InvalidArgumentException {
        if (Integer.toString(eventCode).length() != 3) {
            throw new InvalidArgumentException("Event code '" + eventCode +
                    "' is incorrect. Please provide an event code containing exactly 3 digits.");
        }
    }

}
