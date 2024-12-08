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
    <div>
      <h2>Add New Contact</h2>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="First Name"
          value={form.firstName}
          onChange={(e) => setForm({ ...form, firstName: e.target.value })}
        />
        <input
          placeholder="Last Name"
          value={form.lastName}
          onChange={(e) => setForm({ ...form, lastName: e.target.value })}
        />
        <input
          placeholder="Email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />
        <input
          placeholder="Phone (no hyphens)"
          value={form.phoneNumber}
          onChange={(e) => setForm({ ...form, phoneNumber: e.target.value })}
        />
        <input
          placeholder="Address"
          value={form.address}
          onChange={(e) => setForm({ ...form, address: e.target.value })}
        />
        <button type="submit">Add Contact</button>
      </form>
    </div>
  );
};
