// src/components/ContactsList.tsx
import { useState, useEffect } from "react";
import axios from "axios";
import { AddContact } from "./AddContact";
import { Header } from "./Header";

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

  const refreshContacts = async () => {
    try {
      setLoading(true);
      const response = await axios.get(
        "http://localhost:8080/api/v1/contacts",
        {
          withCredentials: true,
          headers: {
            Accept: "application/json",
          },
        }
      );
      setContacts(response.data.content || []);
      setError("");
    } catch (err) {
      console.error("Error fetching contacts:", err);
      setError("Failed to fetch contacts");
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    refreshContacts();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="mb-8">
          <AddContact onContactAdded={refreshContacts} />
        </div>

        {loading ? (
          <div>Loading...</div>
        ) : error ? (
          <div className="text-red-600">{error}</div>
        ) : (
          <div className="bg-white shadow rounded-lg overflow-hidden">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Email
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Phone
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Address
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {contacts.map((contact) => (
                  <tr key={contact.id} className="hover:bg-gray-50">
                    <td className="px-6 py-4 whitespace-nowrap">
                      {contact.firstName} {contact.lastName}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {contact.email}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {contact.phoneNumber}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      {contact.address}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};
