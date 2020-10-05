package ua.kharkov.repairagency.db.entity;

public enum Status {
    WAIT ("Ждет оплаты",1),
    PAID ("Оплачено",2),
    CANCELLED ("Отменено",3),
    WORK("В работе",4),
    END("Исполнено",5);

    private int id;
    private String name;
    Status() {

    }
    Status(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameId(int id) {
        Status status;
        if(id == 1){
            status = WAIT;
        }else if(id==2){
            status = PAID;
        }else if(id==3){
            status = CANCELLED;
        }
        else if(id==4){
            status = WORK;
        }else{
            status = END;
        }
        return status.getName();

    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                '}';
    }
}
