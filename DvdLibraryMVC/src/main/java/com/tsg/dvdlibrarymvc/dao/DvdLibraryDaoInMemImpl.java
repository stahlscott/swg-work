/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.dvdlibrarymvc.dao;

import com.tsg.dvdlibrarymvc.dto.Dvd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    private static int dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        return new ArrayList(dvdMap.values());
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String reviewerRatingCriteria = criteria.get(SearchTerm.REVIEWER_RATING);
        String notesCriteria = criteria.get(SearchTerm.NOTES);

        Predicate<Dvd> titleMatches;
        Predicate<Dvd> releaseDateMatches;
        Predicate<Dvd> mpaaRatingMatches;
        Predicate<Dvd> studioMatches;
        Predicate<Dvd> directorMatches;
        Predicate<Dvd> reviewerRatingMatches;
        Predicate<Dvd> notesMatches;

        Predicate<Dvd> truePredicate = (d) -> {
            return true;
        };
        
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getTitle().contains(titleCriteria);
        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getReleaseDate().contains(releaseDateCriteria);
        mpaaRatingMatches = (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getMpaaRating().contains(mpaaRatingCriteria);
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getStudio().contains(studioCriteria);
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getDirector().contains(directorCriteria);
        reviewerRatingMatches = (reviewerRatingCriteria == null || reviewerRatingCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getReviewerRating().contains(reviewerRatingCriteria);
        notesMatches = (notesCriteria == null || notesCriteria.isEmpty()) ? truePredicate : 
                (d) -> d.getNotes().contains(notesCriteria);
        
        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(mpaaRatingMatches)
                        .and(studioMatches)
                        .and(directorMatches)
                        .and(reviewerRatingMatches)
                        .and(notesMatches))
                .collect(Collectors.toList());
    }

}
