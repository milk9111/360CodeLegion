package model;

import java.io.Serializable;

/**
 * Created by Josiah on 5/6/2017.
 */
public class Account implements Serializable{

    private Author myAuthor;
    private Reviewer myReviewer;
    private SubprogramChair mySubprogramChair;

    public Account(Author myAuthor, Reviewer myReviewer, SubprogramChair mySubprogramChair) {
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


}
