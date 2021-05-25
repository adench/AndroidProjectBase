package com.xqkj.baselibrary.maintab;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabBaseEntity {
    private TabEntity tabEntity;

    public TabEntity getTabEntity() {
        return tabEntity;
    }

    public TabBaseEntity(String title, int selectedIcon, int unSelectedIcon, Fragment fragment) {
        this.tabEntity = new TabEntity(title, selectedIcon, unSelectedIcon, fragment);
    }

    static class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;
        public Fragment fragment;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon, Fragment fragment) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
            this.fragment = fragment;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }


}
