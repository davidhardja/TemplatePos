package com.example.david.templatepos.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.david.templatepos.Adapter.InventoryAdapter;
import com.example.david.templatepos.Data.Product;
import com.example.david.templatepos.Data.Sale;
import com.example.david.templatepos.MainActivity;
import com.example.david.templatepos.R;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 07/06/2017.
 */

@SuppressLint("ValidFragment")
public class InventoryFragment extends UpdatableFragment {
    protected static final int SEARCH_LIMIT = 0;
    @BindView(R.id.addProductButton)
    Button bAddProduct;
    @BindView(R.id.scanButton)
    Button bScan;
    @BindView(R.id.searchBox)
    EditText etSeachBox;
    @BindView(R.id.productRecycleView)
    RecyclerView rvProduct;

    ViewPager viewPager;
    MainActivity main;

    private List<Map<String, String>> inventoryList;
    private UpdatableFragment saleFragment;

    public InventoryFragment(UpdatableFragment saleFragment) {
        super();
        this.saleFragment = saleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu_fragment, container, false);
        ButterKnife.bind(this, view);
        main = (MainActivity) getActivity();
        viewPager = main.getViewPager();
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();
    }

    private void initUi(){
        bAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPopup();
            }
        });

        etSeachBox.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if (s.length() >= SEARCH_LIMIT) {
                    search();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        search();
    }

    public void addItemToSale(Sale sale){
        List<Sale> resultName = Sale.find(Sale.class, "product_Id = ?", String.valueOf(sale.getProductId()));
        if(resultName.size()==0){
            sale.save();
        }else{
            Sale s = resultName.get(0);
            s.setQuantity(s.getQuantity()+1);
            s.save();
        }

        saleFragment.update();
        viewPager.setCurrentItem(1);
    }

    @Override
    public void update() {
        search();
    }

    public void showPopup() {
        AddProductDialogFragment newFragment = new AddProductDialogFragment(InventoryFragment.this);
        newFragment.show(getFragmentManager(), "");
    }

    private void search(){
        System.out.println("Search");
        String search = etSeachBox.getText().toString();

        if (search.equals("")) {
            List<Product> resultAll = Product.listAll(Product.class);
            showList(resultAll);

            for (int i=0;i<resultAll.size();i++){
                System.out.println("CHECK ITEM: "+resultAll.get(i).getId()+" "+resultAll.get(i).getName()+" "+resultAll.get(i).getBarcode());
            }
        } else {

            List<Product> resultName = Select.from(Product.class)
                    .where(Condition.prop("name").like("%"+search+"%"))
                    .list();
            List<Product> resultBarcode = Select.from(Product.class)
                    .where(Condition.prop("barcode").like(search))
                    .list();

            System.out.println("CHECK SIZEL "+ resultBarcode.size());
            resultName.addAll(resultBarcode);
            showList(resultName);
            if (resultBarcode.isEmpty()) {

            }
        }
    }

    private void showList(List<Product> productList){
        InventoryAdapter inventoryAdapter = new InventoryAdapter(getActivity(),productList,this);
        rvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProduct.setAdapter(inventoryAdapter);
    }


}
