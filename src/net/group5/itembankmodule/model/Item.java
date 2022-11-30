package net.group5.itembankmodule.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Item {
    protected int id;
    protected String domain; // question domain
    protected String itemType; // question type - example mcq, ... enums
    protected String itemText; // question text
    protected String answerKey; // answer key for the question object
    protected String author;
    protected boolean toBeUpdated;
    protected boolean toBeDeleted;

    public Item() {}

    public Item(int id, String domain, String itemType, String itemText, String answerKey, String author, boolean toBeUpdated, boolean toBeDeleted) {
        super();
        this.id = id;
        this.domain = domain;
        this.itemType = itemType;
        this.itemText = itemText;
        this.answerKey = answerKey;
        this.author = author;
        this.toBeUpdated = toBeUpdated;
        this.toBeDeleted = toBeDeleted;
    }

    public Item(String domain, String itemType, String itemText, String answerKey, String author, boolean toBeUpdated, boolean toBeDeleted) {
    	super();
        this.domain = domain;
        this.itemType = itemType;
        this.itemText = itemText;
        this.answerKey = answerKey;
        this.author = author;
        this.toBeUpdated = toBeUpdated;
        this.toBeDeleted = toBeDeleted;
    }

    public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(String answerKey) {
		this.answerKey = answerKey;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isToBeUpdated() {
		return toBeUpdated;
	}

	public void setToBeUpdated(boolean toBeUpdated) {
		this.toBeUpdated = toBeUpdated;
	}

	public boolean isToBeDeleted() {
		return toBeDeleted;
	}

	public void setToBeDeleted(boolean toBeDeleted) {
		this.toBeDeleted = toBeDeleted;
	}

	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getCountry() {
//        return country;
//    }
//    public void setCountry(String country) {
//        this.country = country;
//    }
}