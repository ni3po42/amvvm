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

package ni3po42.android.tractiondemo.models;

import android.app.Fragment;

public class DemoFragmentChoice
{
	private Class<? extends Fragment> fragmentType;
	private String name;
	private String description;
	
	public DemoFragmentChoice(Class<? extends Fragment> fragmentType, String name, String description)
	{
		this.fragmentType = fragmentType;
		this.name = name;
		this.description = description;
	}

	public Class<? extends Fragment> getFragmentType()
	{
		return fragmentType;
	}
	public void setFragmentType(Class<? extends Fragment> viewModelType)
	{
		this.fragmentType = viewModelType;
	}

    public Fragment getFragment()
    {
        Fragment fragment = null;
        try
        {
            fragment = getFragmentType().newInstance();
        }
        catch (Throwable e)
        {
        }
        if (fragment == null)
            throw new RuntimeException("Cannot find View Model : " + getFragmentType().getName());
        return fragment;
    }


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
