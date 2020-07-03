package GUI.Tables;

public interface TableNavigation {

    void sortHigh(int column);

    void sortLow(int column);

    void filter(int column, Object filter);

    void cancel();
}
