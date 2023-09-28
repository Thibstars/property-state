package com.github.thibstars.propertystate.pages;

import java.util.Optional;

/**
 * @author Thibault Helsmoortel
 */
public interface PropertyStateFinder {

    Optional<String> findState();
}
