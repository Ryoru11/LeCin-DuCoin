package ucy.ece318.assignment3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String messageText;
    private String messageDate;
    private String messageType; //Answer or Question

    public void setId(final int pId){
        id = pId;
    }

    public int getId(){
        return id;
    }

    public void setMessageText(final String pMT){
        messageText = pMT;
    }

    public String getMessageText(){
        return messageText;
    }

    public void setMessageDate(final String pMD){
        messageDate = pMD;
    }

    public String getMessageDate(){
        return messageDate;
    }

    public void setMessageType(final String pMT){
        messageType = pMT;
    }

    public String getMessageType(){
        return messageType;
    }



}
