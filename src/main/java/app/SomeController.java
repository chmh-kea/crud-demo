package app;
import app.data.MongoSomeObjectCrud;
import app.data.MySqlSomeObjectCrud;
import app.data.SomeObjectCrud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SomeController
{
    private SomeObjectCrud crud = MongoSomeObjectCrud.getInstance(); //MySqlSomeObjectCrud.getInstance();

    @GetMapping("/")
    public String home()
    {
        return "somepage";
    }

    @GetMapping("/SomeObject")
    public String getAll(ModelMap modelMap)
    {
        List<SomeObject> someObjects = crud.getSomeObjectsCollection();

        if (someObjects != null && someObjects.size() > 0)
        {
            modelMap.addAttribute("editFunction","Create");
            if (!modelMap.containsAttribute("crudFeedback"))
                modelMap.addAttribute("crudFeedback", "Welcome to SomeObject Crud");
        }

        modelMap.addAttribute("someObjects", someObjects);
        return "somepage";
    }

    // Create
    @PostMapping("/SomeObject/Create")
    public String create(@RequestParam("someString") String someString, @RequestParam("someInt") int someInt, ModelMap modelMap)//ModelMap modelMap)
    {
        if (someString != null || someString != "")
        {
            SomeObject someObject = new SomeObject();
            someObject.setSomeString(someString);
            someObject.setSomeInt(someInt);

            if (crud.createSomeObject(someObject))
                modelMap.addAttribute("crudFeedback", "Created SomeObject");
            else
                modelMap.addAttribute("crudFeedback", "Failed creating SomeObject");

        }

        return getAll(modelMap);
    }

    // Read
    @PostMapping("/SomeObject/Edit")
    public String edit(@RequestParam("id") int id, ModelMap modelMap)
    {
        SomeObject someObject = crud.getSomeObject(id);


        if(someObject != null)
        {
            modelMap.addAttribute("editObject",someObject);
            modelMap.addAttribute("crudFeedback", "Editing SomeObject");
        }
        else
            modelMap.addAttribute("crudFeedback", "Failed reading SomeObject");

        return getAll(modelMap);
    }

    // Update
    @PostMapping("/SomeObject/Update")
    public String update(@ModelAttribute("someObject") SomeObject someObject, ModelMap modelMap)
    {
        if (someObject != null)
        {
            if(crud.updateSomeObject(someObject))
                modelMap.addAttribute("crudFeedback", "Updated SomeObject");
            else
                modelMap.addAttribute("crudFeedback", "Failed updating SomeObject");
        }

        return getAll(modelMap);
    }

    // Delete
    @PostMapping("/SomeObject/Delete")
    public String delete(@RequestParam("id") int id, ModelMap modelMap)
    {
        if (crud.deleteSomeObject(id))
            modelMap.addAttribute("crudFeedback", "Deleted SomeObject");
        else
            modelMap.addAttribute("crudFeedback", "Failed deleting SomeObject");

        return getAll(modelMap);
    }
}
