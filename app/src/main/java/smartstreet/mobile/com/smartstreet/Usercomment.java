package smartstreet.mobile.com.smartstreet;

/**
 * Created by Ashutosh on 3/19/2016.
 */
public class Usercomment {

    int _id;
    String Comments;
    String rating;


    public void Comment() {

    }

    public String getComments()
    {

        return Comments;
    }

    public void setComments(String comments)
    {
        Comments = comments;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }
}
