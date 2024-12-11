import React, { useState } from "react";
import axios from "axios";

interface AddContactForm {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  address: string;
}

export const AddContact = ({
  onContactAdded,
}: {
  onContactAdded: () => void;
}) => {
  const [form, setForm] = useState<AddContactForm>({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/v1/contacts", form, {
        withCredentials: true,
      });
      onContactAdded(); // Refresh contact list
      // Clear form
      setForm({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        address: "",
      });
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-2xl mx-auto mb-8">
      <h2 className="text-2xl mb-6 font-semibold">Add Contact</h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              First Name
            </label>
            <input
              type="text"
              value={form.firstName}
              onChange={(e) => setForm({ ...form, firstName: e.target.value })}
              className="w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500 focus:border-green-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Last Name
            </label>
            <input
              type="text"
              value={form.lastName}
              onChange={(e) => setForm({ ...form, lastName: e.target.value })}
              className="w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500 focus:border-green-500"
            />
          </div>
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Email
          </label>
          <input
            type="email"
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
            className="w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500 focus:border-green-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Phone Number
          </label>
          <input
            type="tel"
            value={form.phoneNumber}
            onChange={(e) => setForm({ ...form, phoneNumber: e.target.value })}
            className="w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500 focus:border-green-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Address
          </label>
          <input
            type="text"
            value={form.address}
            onChange={(e) => setForm({ ...form, address: e.target.value })}
            className="w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500 focus:border-green-500"
          />
        </div>

        <button
          type="submit"
          className="w-full bg-green-600 text-white rounded-md py-2 px-4 hover:bg-green-700 mt-4"
        >
          Add Contact
        </button>
      </form>
    </div>
  );
};
