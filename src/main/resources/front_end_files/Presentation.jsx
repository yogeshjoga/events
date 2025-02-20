import React, { useState, useEffect } from 'react';
import { Button, Form, Input, Checkbox, Select, Card } from 'antd';
import localforage from 'localforage';

const saveToIndexedDB = async (newEntry) => {
  try {
    let existingEntries = await localforage.getItem('presentatonFormData'); // Updated key
    if (!existingEntries) {
      existingEntries = [];
    }
    existingEntries.push(newEntry);
    await localforage.setItem('presentatonFormData', existingEntries); // Updated key
    console.log('Data saved to IndexedDB:', existingEntries);
  } catch (error) {
    console.error('Error saving to IndexedDB:', error);
  }
};

const getFromIndexedDB = async () => {
  try {
    const entries = await localforage.getItem('presentatonFormData'); // Updated key
    return entries || [];
  } catch (error) {
    console.error('Error getting data from IndexedDB:', error);
    return [];
  }
};

const Presentation = () => {
  const [isGoldChecked, setIsGoldChecked] = useState(false);
  const [isSilverChecked, setIsSilverChecked] = useState(false);
  const [isAmountChecked, setIsAmountChecked] = useState(false);
  const [isGiftsChecked, setIsGiftsChecked] = useState(false);
  const [isPhoneChecked, setIsPhoneChecked] = useState(false);
  const [isAddressChecked, setIsAddressChecked] = useState(false);
  const [entries, setEntries] = useState([]);

  const onFinish = async (values) => {
    const presentatonFormData = {
      ...values,
      goldAmount: isGoldChecked ? values.goldAmount : 0,
      silverAmount: isSilverChecked ? values.silverAmount : 0,
      amount: isAmountChecked ? values.amount : 0,
      gifts: isGiftsChecked ? values.gifts : 0,
      phoneNumber: isPhoneChecked ? values.phoneNumber : '',
      address: isAddressChecked ? values.address : '',
    };
    console.log('Form submitted:', presentatonFormData);
    alert(`Form submitted successfully! ${JSON.stringify(presentatonFormData)}`);
    
    await saveToIndexedDB(presentatonFormData);
    const updatedEntries = await getFromIndexedDB();
    setEntries(updatedEntries);
  };

  useEffect(() => {
    const fetchEntries = async () => {
      const initialEntries = await getFromIndexedDB();
      setEntries(initialEntries);
    };
    fetchEntries();
  }, []);

  return (
    <div className="min-h-screen w-full p-4 sm:p-6 md:p-8 lg:p-12 flex justify-center items-center bg-gradient-to-b from-slate-950 to-slate-800">
      <div className="w-full p-6 bg-gray-500 text-white max-w-2xl rounded-md shadow-lg">
        <Form layout="vertical" onFinish={onFinish} className="space-y-4">
          {/* Personal Information */}
          <Form.Item label="First Name" name="firstName" rules={[{ required: true, message: 'Please enter first name' }]}>
            <Input placeholder="Enter first name" />
          </Form.Item>

          <Form.Item label="Last Name" name="lastName" rules={[{ required: true, message: 'Please enter last name' }]}>
            <Input placeholder="Enter last name" />
          </Form.Item>

          <Form.Item label="City" name="city" rules={[{ required: true, message: 'Please enter city' }]}>
            <Input placeholder="Enter city" />
          </Form.Item>

          <Card>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">

              {/* Phone Section */}
              <div>
                <Checkbox onChange={(e) => setIsPhoneChecked(e.target.checked)}>Phone</Checkbox>
                {isPhoneChecked && (
                  <Form.Item name="phoneNumber" rules={[{ required: true, message: 'Please enter phone number' }]}>
                    <Input type="number" placeholder="Enter Phone Number" defaultValue="910000000000" />
                  </Form.Item>
                )}
              </div>

              {/* Address Section */}
              <div>
                <Checkbox onChange={(e) => setIsAddressChecked(e.target.checked)}>Address</Checkbox>
                {isAddressChecked && (
                  <Form.Item name="address" rules={[{ required: true, message: 'Please enter address' }]}>
                    <Input type="string" placeholder="Enter address" defaultValue="Not given" />
                  </Form.Item>
                )}
              </div>

              {/* Gold Section */}
              <div>
                <Checkbox onChange={(e) => setIsGoldChecked(e.target.checked)}>Gold</Checkbox>
                {isGoldChecked && (
                  <Form.Item name="goldAmount" rules={[{ required: true, message: 'Please enter gold in gm' }]}>
                    <Input type="number" placeholder="Enter gold in gm" defaultValue="0.0" />
                  </Form.Item>
                )}
              </div>

              {/* Silver Section */}
              <div>
                <Checkbox onChange={(e) => setIsSilverChecked(e.target.checked)}>Silver</Checkbox>
                {isSilverChecked && (
                  <Form.Item name="silverAmount" rules={[{ required: true, message: 'Please enter silver in gm' }]}>
                    <Input type="number" placeholder="Enter silver in gm" defaultValue="0.0" />
                  </Form.Item>
                )}
              </div>

              {/* Amount Section */}
              <div>
                <Checkbox onChange={(e) => setIsAmountChecked(e.target.checked)}>Amount</Checkbox>
                {isAmountChecked && (
                  <Form.Item name="amount" rules={[{ required: true, message: 'Please enter amount' }]}>
                    <Input type="number" placeholder="Enter amount" defaultValue="0.0" />
                  </Form.Item>
                )}
              </div>

              {/* Gifts Section */}
              <div>
                <Checkbox onChange={(e) => setIsGiftsChecked(e.target.checked)}>Gifts</Checkbox>
                {isGiftsChecked && (
                  <Form.Item name="gifts" rules={[{ required: true, message: 'Please enter gifts' }]}>
                    <Input type="string" placeholder="Enter your gifts" defaultValue="Not Given" />
                  </Form.Item>
                )}
              </div>
            </div>
          </Card>

          {/* Status Selection */}
          <Form.Item label="Status" name="status" rules={[{ required: false, message: 'Please select status' }]}>
            <Select placeholder="Select status" defaultValue="taken">
              <Select.Option value="gifted">Gifted</Select.Option>
              <Select.Option value="taken">Taken</Select.Option>
            </Select>
          </Form.Item>

          {/* Submit Button */}
          <Form.Item>
            <Button type="primary" htmlType="submit" block size="large">
              Submit
            </Button>
          </Form.Item>
        </Form>

        <div>
          <h2>Stored Entries:</h2>
          <ul>
            {entries.map((entry, index) => (
              <li key={index}>
                {entry.firstName} {entry.lastName} - {entry.city}, {entry.phoneNumber}, {entry.address}, Gold: {entry.goldAmount}g, Silver: {entry.silverAmount}g, Amount: {entry.amount}, Gifts: {entry.gifts}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Presentation;




