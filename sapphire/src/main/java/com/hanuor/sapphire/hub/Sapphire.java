package com.hanuor.sapphire.hub;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.View;
import android.widget.ImageView;


public  class Sapphire {
    public View getSuggestionView() {
        return suggestionView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    private View suggestionView;
    private ImageView imageView;
    private  Sapphire(ViewBuilder targetBuilder){
        this.imageView = targetBuilder.imageView;
        this.suggestionView = targetBuilder.suggestionView;
    }

    public static class ViewBuilder{
        private View suggestionView;
        private String headerTextId;
        private String footerTextId;
        private ImageView imageView;
        public ViewBuilder(View view){
            this.suggestionView = view;
        }
        public ViewBuilder getHeaderTextId(String id){
            this.headerTextId = id;
            return this;
        }
        public ViewBuilder getFooterTextId(String id){
            this.footerTextId  = id;
            return this;
        }
        public ViewBuilder getImageViewId(String id){
            this.imageView = imageView;
            return this;
        }
        public Sapphire build(){
            return new Sapphire(this);
        }
    }
}
