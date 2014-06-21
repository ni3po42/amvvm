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

package traction.mvc.interfaces;


/**
 * IObservableObject - defines the methods needed to handle sending and receiving events and updates to other object.
 * @author Tim Stratton
 *
 */
public interface IObservableObject
extends IObjectListener, IProxyObservableObject,IPOJO
{	

	/**
	 * Hard wire a property's listener to it's host object. Primarily used when not using a get/set method and instead using a final field.
	 * @param propertyName
	 * @param parentObj
	 * @return
	 */
	<T extends IProxyObservableObject> T registerAs(String propertyName, IProxyObservableObject parentObj);
	
	/**
	 * notifies any object listening that the whole object is claiming an update, not just a particular property
	 */
	void notifyListener();
	
	/**
	 * notifies any object listening that a single property has updated. user this call for non-IObservableObjects.
	 * If you need to update a property that is a IObservableObject, use notifyListener(String,IObservableObject, IObservable) 
	 * @param propertyName
	 */
	void notifyListener(String propertyName);
	
	/**
	 * notifies any object listening that a single property of type IObservableObject. This handles sending the signal and re-wiring
	 * listeners.
	 * @param propertyName
	 * @param oldPropertyValue
	 * @param newPropertyValue
	 */
	void notifyListener(String propertyName, IProxyObservableObject oldPropertyValue, IProxyObservableObject newPropertyValue);

	/**
	 * Registers a listener to the IObservable
	 * @param sourceName : name of source property to track changes and bubble up to inventory
	 * @return : 'this' typed as <T>
	 */
	<T extends IProxyObservableObject> T registerListener(String sourceName, IObjectListener listener);
	
	/**
	 * Remove registration from IObservable. Once called, OnObservableUpdated should no longer signal the listener
	 * @param listener : listener to unregister from IObservable
	 */
	void unregisterListener(String sourceName, IObjectListener listener);

    /**
     * Returns source object that is being extended with the IObservableObject properties
     * @return
     */
    Object getSource();
}
