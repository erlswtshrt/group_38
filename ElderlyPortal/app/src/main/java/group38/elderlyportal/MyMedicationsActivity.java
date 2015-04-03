
package group38.elderlyportal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.*;
import java.util.* ;


public class MyMedicationsActivity extends ListActivity {
    private TextView text; //text above the list

    //IDs for new activities
    public static final int AddAMedicationActivity_ID = 1;
    public static final int EditAMedicationActivity_ID = 2;
    ArrayList<Medicine> medicineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medications);

        //initialize the text before the list
        text = (TextView) findViewById(R.id.mainText);

       // medicineList = getIntent().getParcelableArrayListExtra("list");

        //populate the medicine list with random medicines - for debugging
        medicineList = new ArrayList<Medicine> () ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 1", "Sci Name A", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 1, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 2", "Sci Name G", "Instructions", "5", new GregorianCalendar(2015, 3, 27, 3, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 3", "Sci Name H", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 6, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 4", "Sci Name E", "Instructions", "5", new GregorianCalendar(2015, 3, 27, 3, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 5", "Sci Name F", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 9, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 6", "Sci Name V", "Instructions", "5", new GregorianCalendar(2015, 3, 28, 3, 1))) ;
        medicineList.add(new Medicine (new ArrayList<Dose> (), "Medicine 7", "Sci Name B", "Instructions", "5", new GregorianCalendar(2015, 3, 26, 14, 1))) ;

        // initiate the listadapter to be used for managing the list of medicines
        MedicineListArrayAdapter adapter = new MedicineListArrayAdapter(this, medicineList);

        // assign the list adapter to this class
        setListAdapter(adapter);

        // assign the spinner's listener so it communicates to the list adapter
        final Spinner spinner = (Spinner) findViewById(R.id.spinner) ;
        spinner.setOnItemSelectedListener (new SpinnerListener (adapter)) ;
    }

    /* Spinner listener class that fine tunes the spinner's actions.
       Specifically, when an option of the spinner is selected,
       the spinner tells the list adapter to sort by that string.
    */
    private class SpinnerListener implements AdapterView.OnItemSelectedListener {

        private MedicineListArrayAdapter adapter ; //the adapter

        /* constructor stores a reference to the list adapter
         */
        public SpinnerListener (MedicineListArrayAdapter adapter) {
            super () ;
            this.adapter = adapter ;
        }

        /* When an item is selected, the spinner has the adapter sort by it.
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner) parent ;
            String selected = spinner.getSelectedItem().toString() ;
            MedicineListArrayAdapter adapter = (MedicineListArrayAdapter) getListAdapter() ;
            adapter.sortBy(selected) ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /* When an item in the list is clicked, have it use an intent to start an
       edit medicine activity. The intent is given the Medicine that was clicked.
    */
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        //determine the selected medicine
        super.onListItemClick(list, view, position, id);
        Medicine selectedItem = (Medicine) getListView().getItemAtPosition(position);

        //set the top text - for debugging
        text.setText("You clicked " + selectedItem.getBrandName() + " at position " + position);

        //create intent, store medicine, start intent
        Intent intent = new Intent (this, EditAMedicationActivity.class) ;
        intent.putExtra("Medicine", selectedItem) ;
        startActivityForResult (intent, EditAMedicationActivity_ID) ;
    }

    /* Starts the add a medicine activity.
       This action is performed by the add a medicine button.
     */
    public void addAMedication (View view) {
        Intent intent = new Intent (this, AddAMedicationActivity.class) ;
        startActivityForResult(intent, AddAMedicationActivity_ID) ;
    }

    public void editAMedication (View view) {
        Intent intent = new Intent (this, EditAMedicationActivity.class);
        Button editButton = (Button) view;
        Medicine selectedMedicine = (Medicine) editButton.getTag();
        intent.putExtra("Medicine", selectedMedicine);
        startActivityForResult(intent, EditAMedicationActivity_ID);
    }

    /* Delete a medication from the list.
       This method is called by a delete button, which puts itself as the parameter.
       Each delete button stores its associated medicine as a tag, and so
       it can convey to the adapter which medicine to delete.
     */
    public void deleteMedicine (View view) {
        //sets up dialog prompting the user for confirmation before deleting a medicine
        final View tempView = view;
        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(this);
        deleteAlert.setMessage("Are you sure you want to delete this medication?");
        deleteAlert.setCancelable(true);
        deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //deletes the medicine
                MedicineListArrayAdapter adapter = (MedicineListArrayAdapter) getListAdapter() ;
                Button deleteButton = (Button) tempView ;
                Medicine medicine = (Medicine) deleteButton.getTag() ;
                adapter.remove (medicine) ;
                dialog.cancel();
            }
        });
        deleteAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = deleteAlert.create();
        alert.show();
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("list", medicineList);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case (AddAMedicationActivity_ID): {
                if (resultCode == Activity.RESULT_OK) {

                    // get extras from added medicine
//                    ArrayList<Dose> doses = intent.getParcelableArrayListExtra("doses");
                    String brandName = intent.getStringExtra("brandName");
                    String scientificName = intent.getStringExtra("scientificName");
                    String instructions = intent.getStringExtra("instructions");
                    String numRefills = intent.getStringExtra("numRefills");
                    //GregorianCalendar refillDate = intent.get("refillDate");

                    Medicine temp = new Medicine(null, brandName, scientificName, instructions, numRefills, new GregorianCalendar(2015, 3, 26, 3, 1));
                    medicineList.add(temp);
                    MedicineListArrayAdapter adapter = new MedicineListArrayAdapter(this, medicineList);
                    setListAdapter(adapter);
                    adapter.sortBy("Name");
                }
                break;
            }
            case (EditAMedicationActivity_ID): {
                if (resultCode == Activity.RESULT_OK) {
//                    //what to do with edit
//                    Medicine oldMed = (Medicine) intent.getExtras().getParcelable("old_medicine");
//                    for (int i = 0; i < list.size(); i++) {
//                        Medicine tempMed = list.get(i);
//                        if (oldMed.toString().equals(tempMed.toString())) {
//                         list.remove(i);
//                        }
//                    }
//
//                    String name = intent.getStringExtra("med_name"); //need to get other extras here
//                    Medicine temp = new Medicine(name, new GregorianCalendar(2015, 3, 26, 3, 1), "2 doses");
//                    list.add(temp);
//                    MedicineListArrayAdapter adapter = new MedicineListArrayAdapter(this, list);
//                    setListAdapter(adapter);
//                    adapter.remove(oldMed);
//                    adapter.sortBy("Name");
                }
            }
            break;
        }
    }

}