/**
 * @Author JoeBig7
 * @date 2021/11/25 15:28:29
 */
public class User {

    private String name;

    private Integer age;

    private String address;

    private Double salary;

    private Boolean married;

    public User() {

    }

    public User(String name, Integer age, String address, Double salary, Boolean married) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }
}
