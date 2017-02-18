// Author:	SATAS
// Course:	SDD
// Semester:    Spring, 2017

package model;

public class Scoretracker 
{
    private int score;
    
    public Scoretracker()
    {
        this.score = 0;
    }
    
    public void addToScore(int points)
    {
        this.score = this.score + points; 
    }
    
    public int getScore()
    {
        return this.score;
    }
}