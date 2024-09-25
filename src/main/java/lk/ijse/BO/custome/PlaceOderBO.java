package lk.ijse.BO.custome;

import lk.ijse.BO.SuperBO;

import java.util.List;

public interface PlaceOderBO extends SuperBO {
    List<String> getCustomerId();

    List<String> getItemCode();

}
