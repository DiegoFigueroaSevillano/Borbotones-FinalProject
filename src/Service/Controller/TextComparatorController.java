package Service.Controller;

import Service.Model.TextComparatorModel;
import Service.Service.TextComparatorService;
import Service.View.TextComparatorView;

public class TextComparatorController {

    TextComparatorService service = new TextComparatorService();
    TextComparatorView view = new TextComparatorView();

    public void compare(String originalText, String copyText){

        TextComparatorModel model = service.compare(originalText, copyText);
        view.printMisspellings(model.getMisspellings());
        view.printPercent(model.getPercentage());

    }

}
