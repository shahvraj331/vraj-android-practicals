package com.example.kotlinbasics.recyclerview_and_adapters

import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinbasics.R

data class MenuItems(val imageOfFoodItem: Int, val nameOfFoodItem: String) {
    companion object {
        fun getMenu(context: Context): MutableList<MenuItems> {
            val menuItems: MutableList<MenuItems> = mutableListOf()
            menuItems.add(MenuItems(R.drawable.bread, context.getString(R.string.bread)))
            menuItems.add(MenuItems(R.drawable.cheesecake, context.getString(R.string.cheese_cake)))
            menuItems.add(MenuItems(R.drawable.chocolate_bar, context.getString(R.string.chocolate_bar)))
            menuItems.add(MenuItems(R.drawable.doughnut, context.getString(R.string.doughnut)))
            menuItems.add(MenuItems(R.drawable.egg, context.getString(R.string.egg)))
            menuItems.add(MenuItems(R.drawable.french_fries, context.getString(R.string.french_fries)))
            menuItems.add(MenuItems(R.drawable.hamburger, context.getString(R.string.hamburger)))
            menuItems.add(MenuItems(R.drawable.hot_dog, context.getString(R.string.hot_dog)))
            menuItems.add(MenuItems(R.drawable.ice_cream_cone, context.getString(R.string.ice_cream_cone)))
            menuItems.add(MenuItems(R.drawable.ice_cream_sundae, context.getString(R.string.ice_cream_sundae)))
            menuItems.add(MenuItems(R.drawable.noodles, context.getString(R.string.noodles)))
            menuItems.add(MenuItems(R.drawable.pizza, context.getString(R.string.pizza)))
            menuItems.add(MenuItems(R.drawable.popcorn, context.getString(R.string.popcorn)))
            menuItems.add(MenuItems(R.drawable.strawberry, context.getString(R.string.strawberry)))
            menuItems.add(MenuItems(R.drawable.taco, context.getString(R.string.taco)))
            return menuItems
        }

        fun addRecentItems(context: Context, sharedPref: SharedPreferences, editor: SharedPreferences.Editor,
            selectedItems: MutableList<String>, favouriteFoodItems: MutableList<MenuItems>) {
            removeRecentItems(context, sharedPref, editor)
            editor.putInt(context.getString(R.string.total_items_in_recent), selectedItems.count())
            for (keyIndex in 1..selectedItems.count()) {
                editor.putString(context.getString(R.string.key_id, keyIndex), selectedItems[keyIndex - 1])
            }
            editor.apply()
            addFavouriteItems(context, sharedPref, editor, favouriteFoodItems)
        }

        fun addFavouriteItems(context: Context, sharedPref: SharedPreferences, editor: SharedPreferences.Editor, favouriteFoodItems: MutableList<MenuItems>) {
            var currentFavStatus: String?
            val allItems = getMenu(context)
            for (itemIndex in 0..14) {
                currentFavStatus = sharedPref.getString(allItems[itemIndex].nameOfFoodItem, null)
                if (currentFavStatus == null) {
                    editor.putString(allItems[itemIndex].nameOfFoodItem, context.getString(R.string.no))
                } else if (currentFavStatus == context.getString(R.string.no)) {
                    if (favouriteFoodItems.contains(allItems[itemIndex])) {
                        editor.putString(allItems[itemIndex].nameOfFoodItem, context.getString(R.string.yes))
                    }
                }
            }
            editor.apply()
        }

        fun removeRecentItems(context: Context, sharedPref: SharedPreferences?, editor: SharedPreferences.Editor?) {
            val totalFoodItemsInRecent = sharedPref?.getInt(context.getString(R.string.total_items_in_recent),0)
            totalFoodItemsInRecent?.let {
                for (keyIndex in 1..it) {
                    editor?.remove(context.getString(R.string.key_id, keyIndex))
                }
            }
            editor?.putInt(context.getString(R.string.total_items_in_recent),0)
            editor?.apply()
        }

        fun removeFavouriteItems(context: Context, editor: SharedPreferences.Editor?) {
            getMenu(context).forEach {
                editor?.putString(it.nameOfFoodItem, context.getString(R.string.no))
            }
            editor?.apply()
        }

        fun retrieveRecentItems(context: Context?, sharedPref: SharedPreferences?): MutableList<MenuItems> {
            val recentMenuItems: MutableList<MenuItems> = mutableListOf()
            val noOfItems = sharedPref?.getInt(context?.getString(R.string.total_items_in_recent), 0)
            if (noOfItems != null && noOfItems > 0) {
                for (index in 1..noOfItems) {
                    val currentFoodItem = sharedPref.getString(context?.getString(R.string.key_id, index), context?.getString(R.string.null_text))
                    if (context != null) {
                        getMenu(context).forEach {
                            if (it.nameOfFoodItem == currentFoodItem) {
                                recentMenuItems.add(it)
                            }
                        }
                    }
                }
            }
            return recentMenuItems
        }

        fun retrieveFavouriteItems(context: Context?, sharedPref: SharedPreferences?): MutableList<MenuItems> {
            val favouriteMenuItems: MutableList<MenuItems> = mutableListOf()
            if (context != null) {
                getMenu(context).forEach {
                    val isFavourite = sharedPref?.getString(it.nameOfFoodItem, null)
                    if (isFavourite == context.getString(R.string.yes)) {
                        favouriteMenuItems.add(it)
                    }
                }
            }
            return favouriteMenuItems
        }
    }
}