package model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Josiah on 5/6/2017.
 */
public class Account implements Serializable {

    private Author myAuthor;
    private Reviewer myReviewer;
    private SubprogramChair mySubprogramChair;
    private UUID myID;
    private String myUsername;
    
    public Account(String theUsername) {
    	this.myID = UUID.randomUUID();
    	this.myUsername = theUsername;
    }
    
    public Account(Author myAuthor, Reviewer myReviewer, SubprogramChair mySubprogramChair) {
    	this.myID = UUID.randomUUID();
        this.myAuthor = myAuthor;
        this.myReviewer = myReviewer;
        this.mySubprogramChair = mySubprogramChair;
    }

    public Author getMyAuthor() {
        return myAuthor;
    }

    public void setMyAuthor(Author myAuthor) {
        this.myAuthor = myAuthor;
    }

    public Reviewer getMyReviewer() {
        return myReviewer;
    }

    public void setMyReviewer(Reviewer myReviewer) {
        this.myReviewer = myReviewer;
    }

    public SubprogramChair getMySubprogramChair() {
        return mySubprogramChair;
    }

    public void setMySubprogramChair(SubprogramChair mySubprogramChair) {
        this.mySubprogramChair = mySubprogramChair;
    }
    
    public String getMyUsername() {
    	return this.myUsername;
    }
    
    public UUID getMyID() {
    	return this.myID;
    }


}
