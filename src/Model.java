public class Model {
    String[] status;
    String praca, dom, szkola;

    public String getPraca() {
        return praca;
    }

    public String getDom() {
        return dom;
    }

    public String getSzkola() {
        return szkola;
    }

    public void setPraca(String praca) {
        this.praca = praca;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public void setSzkola(String szkola) {
        this.szkola = szkola;
    }

    public String[] getStatus() {
        return status;
    }

    public Model() {
        status = new String[]{"new", "modified", "saved"};
    }
}

