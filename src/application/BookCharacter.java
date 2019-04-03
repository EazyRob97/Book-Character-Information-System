package application;

import java.io.Serializable;

/**
 * @date 2018/12/8 6:54 PM
 */
public class BookCharacter implements Serializable {
    private static final long serialVersionUID = 1000L;

    private String name;
    private String gender;
    private String description;

    public BookCharacter(String name, String gender, String description) {
        this.name = name;
        this.gender = gender;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
