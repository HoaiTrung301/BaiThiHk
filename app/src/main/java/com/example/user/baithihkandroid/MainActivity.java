package com.example.user.baithihkandroid;

import android.database.DataSetObserver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    ListView lvDS;
    ArrayList list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        lvDS = findViewById(R.id.lvDS);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvDS.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                AlertDialog.Builder buider = new AlertDialog.Builder(MainActivity.this);
                View addForm = getLayoutInflater().inflate(R.layout.activity_layout__add, null);
                final EditText txtUser = addForm.findViewById(R.id.txtAdd);
                Button btnAddUser = addForm.findViewById(R.id.btnAddUser);
                Button btnCancelAdd = addForm.findViewById(R.id.btnCancelAdd);

                buider.setView(addForm);
                final AlertDialog dialog = buider.create();
                dialog.show();
                btnAddUser.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view){
                        list.add(txtUser.getText()+"");
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });

                btnCancelAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
        registerForContextMenu(lvDS);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater Menu = getMenuInflater();
        Menu.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AlertDialog.Builder builder_2 = new AlertDialog.Builder(MainActivity.this);
        View editForm = getLayoutInflater().inflate(R.layout.activity_layout_edit, null);
        final EditText txtEdit = editForm.findViewById(R.id.edEdit);
        Button btnEdit = editForm.findViewById(R.id.btnEdit);
        Button btnCancelEdit = editForm.findViewById(R.id.btnCancelEdit);

        switch(item.getItemId()){
            case R.id.opEdit :
                builder_2.setView(editForm);
                final AlertDialog dialog = builder_2.create();
                dialog.show();
                txtEdit.setText(list.get(info.position).toString());
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view){
                        list.set(info.position, txtEdit.getText()+"");
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Ðã s?a", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                btnCancelEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;


            case R.id.opDel :
                list.remove(info.position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Ðã xóa", Toast.LENGTH_SHORT).show();

        }

        return super.onContextItemSelected(item);


    }
}
