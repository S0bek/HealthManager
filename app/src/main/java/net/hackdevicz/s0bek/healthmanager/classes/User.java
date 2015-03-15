package net.hackdevicz.s0bek.healthmanager.classes;

/**
 * Created by s0bek on 14/03/2015.
 */
public class User {

    public double size;
    public double weight;
    public int age;

    public String sexe;

    public double imc;
    public double img;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getImg() {
        return img;
    }

    public void setImg(double img) {
        this.img = img;
    }

    public void imc_calculate() {
        this.imc = this.weight / (this.size * this.size);
    }

    public void img_calculate() {

        int sex_value;
        if (sexe == "Homme") {
            sex_value = 1;
        } else {
            sex_value = 0;
        }

        //this.img = (Float) (((Float) (1.20 * this.imc) + (0.23 * this.age)) - (sex_value * 10.8) - 5.4);
        this.img = (1.20 * this.imc) + (0.23 * this.age) - (sex_value * 10.8) - 5.4;
    }
}
