// src/components/ContactDetails.tsx
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { Header } from "./Header";

interface Contact {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  address: string;
}

export const ContactDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [contact, setContact] = useState<Contact | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    const fetchContact = async () => {
      try {
        setLoading(true);
        const response = await axios.get(
          `http://localhost:8080/api/v1/contacts/${id}`,
          {
            withCredentials: true,
            signal: controller.signal,
          }
        );
        setContact(response.data);
      } catch (err) {
        if (axios.isCancel(err)) return;
        setError("Failed to load contact");
      } finally {
        setLoading(false);
      }
    };

    fetchContact();

    return () => {
      controller.abort(); // Cancel request if component unmounts
    };
  }, [id]); // Only re-run if ID changes

  if (loading) return <div className="text-center p-4">Loading...</div>;
  if (error) return <div className="text-red-600 p-4">{error}</div>;
  if (!contact) return <div className="text-center p-4">Contact not found</div>;

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <div className="max-w-3xl mx-auto px-4 py-6">
        <button
          onClick={() => navigate("/contacts")}
          className="mb-4 text-green-600 hover:text-green-700 flex items-center"
        >
          ← Back to Contacts
        </button>
        <div className="bg-white shadow rounded-lg overflow-hidden">
          <div className="p-6">
            <h2 className="text-2xl font-semibold text-gray-900 mb-6">
              {contact.firstName} {contact.lastName}
            </h2>
            <dl className="grid grid-cols-1 gap-6">
              <div>
                <dt className="text-sm font-medium text-gray-500">Email</dt>
                <dd className="mt-1 text-lg text-gray-900">{contact.email}</dd>
              </div>
              <div>
                <dt className="text-sm font-medium text-gray-500">Phone</dt>
                <dd className="mt-1 text-lg text-gray-900">
                  {contact.phoneNumber}
                </dd>
              </div>
              <div>
                <dt className="text-sm font-medium text-gray-500">Address</dt>
                <dd className="mt-1 text-lg text-gray-900">
                  {contact.address}
                </dd>
              </div>
            </dl>
          </div>
        </div>
      </div>
    </div>
  );
};
