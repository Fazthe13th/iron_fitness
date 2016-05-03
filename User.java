/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iron_fitness;


import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wwwfa
 */
public class User {
    private final SimpleStringProperty ID;
    private final SimpleStringProperty member_name;
    private final SimpleStringProperty member_age;
    private final SimpleStringProperty member_height;
    private final SimpleStringProperty member_weight;
    private final SimpleStringProperty member_gender;
    private final SimpleStringProperty member_location;
    private final SimpleStringProperty member_contract;
    private final SimpleStringProperty member_bloodgroup;
    private final SimpleStringProperty member_ocupation;
    private final SimpleStringProperty member_name_reg_date;
    
    public User(String memid,String name,String age,String height,String weight,String gender,String location,String contract,String bloodgroup,String ocupation,String addate)
    {
        this.ID = new SimpleStringProperty(memid);
        this.member_name = new SimpleStringProperty(name);
        this.member_age = new SimpleStringProperty(age);
        this.member_height = new SimpleStringProperty(height);
        this.member_weight = new SimpleStringProperty(weight);
        this.member_gender = new SimpleStringProperty(gender);
        this.member_location = new SimpleStringProperty(location);
        this.member_contract = new SimpleStringProperty(contract);
        this.member_bloodgroup = new SimpleStringProperty(bloodgroup);
        this.member_ocupation = new SimpleStringProperty(ocupation);
        this.member_name_reg_date = new SimpleStringProperty(addate);
    }
    public String getMemid()
    {
        return ID.get();
    }
    public void setMemid(String memid)
    {
        ID.set(memid);
    }
    public String getName()
    {
        return member_name.get();
    }
    public void setName(String name)
    {
        member_name.set(name);
    }
    public String getAge()
    {
        return member_age.get();
    }
    public void setAge(String age)
    {
        member_age.set(age);
    }
    public String getHeight()
    {
        return member_height.get();
    }
    public void setHeight(String height)
    {
        member_height.set(height);
    }
    public String getWeight()
    {
        return member_weight.get();
    }
    public void setWeight(String weight)
    {
        member_weight.set(weight);
    }
    public String getGender()
    {
        return member_gender.get();
    }
    public void setGender(String gender)
    {
        member_gender.set(gender);
    }
    public String getLocation()
    {
        return member_location.get();
    }
    public void setLocation(String location)
    {
        member_location.set(location);
    }
    public String getOcupation()
    {
        return member_ocupation.get();
    }
    public void setOcupation(String ocupation)
    {
        member_ocupation.set(ocupation);
    }
    public String getContract()
    {
        return member_contract.get();
    }
    public void setContract(String contract)
    {
        member_contract.set(contract);
    }
    public String getBloodgroup()
    {
        return member_bloodgroup.get();
    }
    public void setBloodgroup(String bloodgroup)
    {
        member_bloodgroup.set(bloodgroup);
    }
    public String getAddate()
    {
        return member_name_reg_date.get();
    }
    public void setAddate(String addate)
    {
        member_name_reg_date.set(addate);
    }
}
