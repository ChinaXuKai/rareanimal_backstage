package test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 22:11
 */
public class ListTest {

    @Test
    public void test() {
        List<String> animalLabelList = new ArrayList<>();
        animalLabelList.add("label-test1");
        animalLabelList.add("label-test2");
        animalLabelList.add("label-test3");
        animalLabelList.add("label-test4");

        String animalLabels = null;
        for (int i = 0; i < animalLabelList.size(); i++){
            String label = animalLabelList.get(i);
            if (animalLabels != null){
                animalLabels = animalLabels + "、" + label;
            }else{
                animalLabels = label;
            }
        }

        System.out.println(animalLabels);
    }

}
