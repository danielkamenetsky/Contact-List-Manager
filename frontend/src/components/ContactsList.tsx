// src/components/ContactsList.tsx
import { useState, useEffect } from "react";
import axios from "axios";
import { AddContact } from "./AddContact";

interface Contact {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  address: string;
}
export const ContactsList = () => {
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const refreshContacts = () => {
    setLoading(true);
    axios
      .get("http://localhost:8080/api/v1/contacts", { withCredentials: true })
      .then((res) => {
        setContacts(res.data.content || []);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Failed to fetch contacts");
        setLoading(false);
      });
  };

  useEffect(() => {
    refreshContacts();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h1>Contacts</h1>
      <AddContact onContactAdded={refreshContacts} />
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {contacts.map((contact) => (
            <tr key={contact.id}>
              <td>
                {contact.firstName} {contact.lastName}
              </td>
              <td>{contact.email}</td>
              <td>{contact.phoneNumber}</td>
              <td>{contact.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
