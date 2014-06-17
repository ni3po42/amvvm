/* Copyright 2013 Tim Stratton

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package amvvm.implementations.ui.viewbinding;

import android.content.Context;
import android.widget.BaseAdapter;

import amvvm.implementations.BindingInventory;
import amvvm.implementations.observables.ObservableObject;

/**
 *
 */
public abstract class ProxyAdapter<T>
    extends ObservableObject
{
    public static class ProxyAdapterArgument
    {
        private Context context;
        private int layoutId = -1;
        private BindingInventory inventory;

        public Context getContext()
        {
            return context;
        }

        public ProxyAdapterArgument setContext(Context context)
        {
            this.context = context;
            return this;
        }

        public int getLayoutId()
        {
            return layoutId;
        }

        public ProxyAdapterArgument setLayoutId(int layoutId)
        {
            this.layoutId = layoutId;
            return this;
        }

        public BindingInventory getInventory()
        {
            return inventory;
        }

        public ProxyAdapterArgument setInventory(BindingInventory inventory)
        {
            this.inventory = inventory;
            return this;
        }
    }

    protected abstract int indexOf(T value);

    protected abstract BaseAdapter getProxyAdapter(ProxyAdapterArgument arg);

    //protected abstract void clearProxyAdapter();

    public interface IAdapterLayout
    {
        void setLayoutId(int layoutId);
    }
}
