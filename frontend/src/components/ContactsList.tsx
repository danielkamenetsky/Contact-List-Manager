// src/components/ContactsList.tsx
import { useState, useEffect } from "react";
import axios from "axios";
import { AddContact } from "./AddContact";
import { Header } from "./Header";
import { useNavigate } from "react-router-dom";

interface Contact {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  address: string;
}

export const ContactsList = () => {
  const navigate = useNavigate();
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const refreshContacts = async (page = 0) => {
    try {
      setLoading(true);
      const response = await axios.get(
        `http://localhost:8080/api/v1/contacts?page=${page}`,
        {
          withCredentials: true,
          headers: {
            Accept: "application/json",
          },
        }
      );
      setContacts(response.data.content || []);
      setTotalPages(response.data.totalPages);
      setCurrentPage(page);
      setError("");
    } catch (err) {
      console.error("Error fetching contacts:", err);
      setError("Failed to fetch contacts");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    refreshContacts(0);
  }, []);
  if (loading) return <div>Loading...</div>;

  const Pagination = () => (
    <div className="mt-4 flex justify-center space-x-2">
      <button
        onClick={() => refreshContacts(currentPage - 1)}
        disabled={currentPage === 0}
        className="px-4 py-2 bg-green-600 text-white rounded disabled:bg-gray-300"
      >
        Previous
      </button>
      <span className="px-4 py-2">
        Page {currentPage + 1} of {totalPages}
      </span>
      <button
        onClick={() => refreshContacts(currentPage + 1)}
        disabled={currentPage === totalPages - 1}
        className="px-4 py-2 bg-green-600 text-white rounded disabled:bg-gray-300"
      >
        Next
      </button>
    </div>
  );

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      {error && <div className="text-red-600 p-4">{error}</div>}
      <div className="max-w-7xl mx-auto px-4 py-6">
        <AddContact onContactAdded={() => refreshContacts(0)} />
        <div className="bg-white shadow rounded-lg mt-6">
          <ul className="divide-y divide-gray-200">
            {contacts.map((contact) => (
              <li
                key={contact.id}
                className="hover:bg-gray-50 p-4 cursor-pointer"
                onClick={() => navigate(`/contacts/${contact.id}`)}
              >
                <div className="flex items-center">
                  <div className="w-10 h-10 bg-green-100 rounded-full flex items-center justify-center">
                    <span className="text-green-800 font-semibold">
                      {contact.firstName[0]}
                    </span>
                  </div>
                  <div className="ml-4">
                    <div className="text-sm font-medium text-gray-900">
                      {contact.firstName} {contact.lastName}
                    </div>
                  </div>
                </div>
              </li>
            ))}
          </ul>
          <Pagination />
        </div>
      </div>
    </div>
  );
};
