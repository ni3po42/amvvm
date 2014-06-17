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

import amvvm.implementations.ui.UIEvent;
import amvvm.implementations.ui.UIProperty;
import amvvm.interfaces.ICommand;
import amvvm.interfaces.IUIElement;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


/**
 * Extends the AdapterViewBinding to handle binding to a Spinner.
 * @author Tim Stratton 
 *
 * @param <T> : type of item in the spinner
 */
public class SpinnerViewBinding<T>
extends AdapterViewBinding<T>
implements OnItemSelectedListener
{
    public final UIEvent<ICommand.CommandArgument> SelectedChoice = new UIEvent<ICommand.CommandArgument>(this, "SelectedChoice");
    public final UIProperty<Integer> SelectedChoiceIndex = new  UIProperty<Integer>(this, "SelectedChoiceIndex");

    private OnItemSelectedListener nullListener = new OnItemSelectedListener()
    {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tryResettingListener();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            tryResettingListener();
        }

        private void tryResettingListener()
        {
            if (getWidget() != null)
                getWidget().setOnItemSelectedListener(SpinnerViewBinding.this);
        }
    };

    public SpinnerViewBinding()
    {
        SelectedChoiceIndex.setUIUpdateListener(new IUIElement.IUIUpdateListener<Integer>() {
            @Override
            public void onUpdate(Integer value) {
                SelectedChoiceIndex.setTempValue(value);
                onAdapterChanged();
            }
        });
    }

    @Override
    protected void initialise() throws Exception
    {
        super.initialise();
        if (getWidget() != null)
            getWidget().setOnItemSelectedListener(nullListener);

    }

    @Override
    protected void onAdapterChanged()
    {
        if (getWidget() == null)
            return;

        getWidget().setOnItemSelectedListener(nullListener);
        getWidget().setSelection(SelectedChoiceIndex.getTempValue());
    }

    @Override
	public void detachBindings()
	{
        if (getWidget() != null)
            getWidget().setOnItemSelectedListener(null);
        super.detachBindings();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemSelected(AdapterView<?> view, View arg1, int position, long arg3)
	{
        SelectedChoiceIndex.sendUpdate(position);
        SelectedChoice.execute(null);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
        SelectedChoiceIndex.sendUpdate(-1);
        SelectedChoice.execute(null);
	}
}
