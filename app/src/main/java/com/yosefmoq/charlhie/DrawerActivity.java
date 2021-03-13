package com.yosefmoq.charlhie;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.yosefmoq.charlhie.Base.BaseActivity;
import com.yosefmoq.charlhie.databinding.ActivityDrawerBinding;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.repository.local.MyDatabase;
import com.yosefmoq.charlhie.ui.FavorateFragment;
import com.yosefmoq.charlhie.ui.Gallary.GalleryFragment;
import com.yosefmoq.charlhie.ui.UserFragment;
import com.yosefmoq.charlhie.ui.home.HomeFragment;
import com.yosefmoq.charlhie.ui.mv.HomeViewModel;
import com.yosefmoq.charlhie.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DrawerActivity extends BaseActivity<ActivityDrawerBinding, HomeViewModel> {
    public static ArraySet<String> subCategories = new ArraySet<>();
    public static HashMap<String, ArrayList<Category>> subCategoryHashMap = new HashMap<>();
    CategoryAdapter categoryAdapter;
    public HashMap<String, ArraySet<String>> categoryHashMap = new HashMap<>();
    ArrayList<CategoryModel> categoryModels = new ArrayList<>();
    ArrayList<CategoryModel> categoryModels1 = new ArrayList<>();
    public DrawerLayout drawer;
    Menu menu;
    public NavigationView navigationView;
    MenuItem prevMenuItem;
    RecyclerView rvCategory;
    int selectedPosision = 0;

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public int getBindingVariable() {
        return 0;
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initItems() {
        getViewModel().getCategories();
        Menu menu2 = ((ActivityDrawerBinding) getViewDataBinding()).include.include.bottomNavigationView.getMenu();
        menu = menu2;
        menu2.getItem(0).setIcon(R.drawable.selected_home);
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public void initClicks() {
        getViewModel().categoryMutableLiveData.observe(this, categories -> {
            handleCategoryHashMap(categories);

        });
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.fragments.add(new HomeFragment());
        myPagerAdapter.fragments.add(new SearchFragment());
        myPagerAdapter.fragments.add(new GalleryFragment());
        myPagerAdapter.fragments.add(new FavorateFragment());
        myPagerAdapter.fragments.add(UserFragment.newInstance());
        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setAdapter(myPagerAdapter);
        ((ActivityDrawerBinding) getViewDataBinding()).include.include.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public final boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_account:
                        selectedPosision = 4;
                        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setCurrentItem(4);
                        break;
                    case R.id.nav_cart:
                        selectedPosision = 2;
                        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setCurrentItem(2);
/*                        selectedUn();
                        menu.getItem(0).setIcon(R.drawable.home);
                        menu.getItem(1).setIcon(R.drawable.search);
                        menu.getItem(2).setIcon(R.drawable.selected_cart);
                        menu.getItem(3).setIcon(R.drawable.favorite);
                        menu.getItem(4).setIcon(R.drawable.account);*/
                        break;
                    case R.id.nav_fav:
                        selectedPosision = 3;
                        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setCurrentItem(3);
/*
                        menu.getItem(0).setIcon(R.drawable.home);
                        menu.getItem(1).setIcon(R.drawable.search);
                        menu.getItem(2).setIcon(R.drawable.unselectedcart);
                        menu.getItem(3).setIcon(R.drawable.selected_heart);
                        menu.getItem(4).setIcon(R.drawable.account);
*/
                        break;
                    case R.id.nav_home:
                        selectedPosision = 0;
                        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setCurrentItem(0);
                        selectedUn();
/*
                        menuItem.setIcon(R.drawable.selected_home);
                        menu.getItem(0).setIcon(R.drawable.selected_home);
                        menu.getItem(1).setIcon(R.drawable.search);
                        menu.getItem(2).setIcon(R.drawable.unselectedcart);
                        menu.getItem(3).setIcon(R.drawable.favorite);
                        menu.getItem(4).setIcon(R.drawable.account);
*/
                        break;
                    case R.id.nav_search:
                        selectedPosision = 1;
                        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setCurrentItem(1);
/*
                        menu.getItem(0).setIcon(R.drawable.home);
                        menu.getItem(1).setIcon(R.drawable.selected_search);
                        menu.getItem(2).setIcon(R.drawable.unselectedcart);
                        menu.getItem(3).setIcon(R.drawable.favorite);
                        menu.getItem(4).setIcon(R.drawable.account);
*/
                        break;
                }
                return true;
            }
        });
        ((ActivityDrawerBinding) getViewDataBinding()).include.include.navHostFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (DrawerActivity.this.prevMenuItem != null) {
                    DrawerActivity.this.prevMenuItem.setChecked(false);
                } else {
                    ((ActivityDrawerBinding) DrawerActivity.this.getViewDataBinding()).include.include.bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                ((ActivityDrawerBinding) DrawerActivity.this.getViewDataBinding()).include.include.bottomNavigationView.getMenu().getItem(position).setChecked(true);
                DrawerActivity drawerActivity = DrawerActivity.this;
                drawerActivity.prevMenuItem = ((ActivityDrawerBinding) drawerActivity.getViewDataBinding()).include.include.bottomNavigationView.getMenu().getItem(position);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void selectedUn() {
        MenuItem menuItem = this.prevMenuItem;
        if (menuItem == null) {
            return;
        }
        if (menuItem.getItemId() == R.id.nav_cart) {
            this.prevMenuItem.setIcon(R.drawable.unselectedcart);
        } else if (this.prevMenuItem.getItemId() == R.id.nav_fav) {
            this.prevMenuItem.setIcon(R.drawable.favorite);
        } else if (this.prevMenuItem.getItemId() == R.id.nav_search) {
            this.prevMenuItem.setIcon(R.drawable.selected_search);
        } else if (this.prevMenuItem.getItemId() == R.id.nav_home) {
            this.prevMenuItem.setIcon(R.drawable.home);
        }
    }

    @Override // com.yosefmoq.charlhie.Base.BaseActivity
    public HomeViewModel getViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.categoryAdapter = new CategoryAdapter(this, this.categoryModels);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvCategories);
        this.rvCategory = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.rvCategory.setAdapter(this.categoryAdapter);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.drawer, menu2);
        return true;
    }

    /* access modifiers changed from: private */
    public void handleCategoryHashMap(ArrayList<Category> ca) {
        Iterator<Category> it = ca.iterator();
        while (it.hasNext()) {
            Category productResponse = it.next();
            if (!subCategoryHashMap.containsKey(productResponse.getSubCategory())) {
                ArrayList<Category> listInHash = new ArrayList<>();
                listInHash.add(productResponse);
                subCategoryHashMap.put(productResponse.getSubCategory(), listInHash);
            } else {
                subCategoryHashMap.get(productResponse.getSubCategory()).add(productResponse);
            }
            subCategories.add(productResponse.getSubCategory());
            if (!this.categoryHashMap.containsKey(productResponse.getCategory())) {
                ArraySet<String> subCategories2 = new ArraySet<>();
                subCategories2.add(productResponse.getSubCategory());
                this.categoryHashMap.put(productResponse.getCategory(), subCategories2);
            } else {
                this.categoryHashMap.get(productResponse.getCategory()).add(productResponse.getSubCategory());
            }
        }
        this.categoryHashMap.forEach((s, strings) -> {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setSubCategories(strings);
            categoryModel.setCategoryName(s);
            this.categoryModels1.add(categoryModel);
            this.categoryAdapter.update(this.categoryModels1);

        });

    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
        refresh();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 5;
        private ArrayList<Fragment> fragments = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getStringExtra("done").equalsIgnoreCase("done")) {
            showSuccess("Je betaling is goed ontvangen.  Dank je!");
        }
    }
    public void refresh(){
        int count = new MyDatabase(this).getAnnal();
        int favCount = new MyDatabase(this).getFavorite().size();
        if ( favCount> 0) {
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_fav).setVisible(true);
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_fav).setNumber(favCount);
        } else {
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_fav).setVisible(false);

        }
        if (count >= 1) {
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_cart).setVisible(true);
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_cart).setNumber(count);
        } else
            getViewDataBinding().include.include.bottomNavigationView.getOrCreateBadge(R.id.nav_cart).setVisible(false);

    }
}
