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
  const [isEditing, setIsEditing] = useState(false);
  const [editForm, setEditForm] = useState<Contact | null>(null);

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
        setEditForm(response.data);
      } catch (err) {
        if (axios.isCancel(err)) return;
        setError("Failed to load contact");
      } finally {
        setLoading(false);
      }
    };

    fetchContact();

    return () => {
      controller.abort();
    };
  }, [id]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/api/v1/contacts/${id}`,
        editForm,
        { withCredentials: true }
      );
      setContact(response.data);
      setIsEditing(false);
    } catch (err) {
      setError("Failed to update contact");
    }
  };

  const renderEditForm = () => (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <label className="block text-sm font-medium text-gray-500">
          First Name
        </label>
        <input
          type="text"
          value={editForm?.firstName || ""}
          onChange={(e) =>
            setEditForm({ ...editForm!, firstName: e.target.value })
          }
          className="mt-1 w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-500">
          Last Name
        </label>
        <input
          type="text"
          value={editForm?.lastName || ""}
          onChange={(e) =>
            setEditForm({ ...editForm!, lastName: e.target.value })
          }
          className="mt-1 w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-500">Email</label>
        <input
          type="email"
          value={editForm?.email || ""}
          onChange={(e) => setEditForm({ ...editForm!, email: e.target.value })}
          className="mt-1 w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-500">
          Phone Number
        </label>
        <input
          type="tel"
          value={editForm?.phoneNumber || ""}
          onChange={(e) =>
            setEditForm({ ...editForm!, phoneNumber: e.target.value })
          }
          className="mt-1 w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div>
        <label className="block text-sm font-medium text-gray-500">
          Address
        </label>
        <input
          type="text"
          value={editForm?.address || ""}
          onChange={(e) =>
            setEditForm({ ...editForm!, address: e.target.value })
          }
          className="mt-1 w-full p-2 border rounded bg-gray-50 focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="flex space-x-4">
        <button
          type="submit"
          className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
        >
          Save Changes
        </button>
        <button
          type="button"
          onClick={() => setIsEditing(false)}
          className="border border-gray-300 px-4 py-2 rounded hover:bg-gray-50"
        >
          Cancel
        </button>
      </div>
    </form>
  );

  if (loading) return <div className="text-center p-4">Loading...</div>;
  if (error) return <div className="text-red-600 p-4">{error}</div>;
  if (!contact) return <div className="text-center p-4">Contact not found</div>;

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <div className="max-w-3xl mx-auto px-4 py-6">
        <div className="flex justify-between items-center mb-4">
          <button
            onClick={() => navigate("/contacts")}
            className="text-green-600 hover:text-green-700 flex items-center"
          >
            ← Back to Contacts
          </button>
          {!isEditing && (
            <button
              onClick={() => {
                setEditForm(contact);
                setIsEditing(true);
              }}
              className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
            >
              Edit Contact
            </button>
          )}
        </div>

        <div className="bg-white shadow rounded-lg overflow-hidden">
          <div className="p-6">
            {isEditing ? (
              renderEditForm()
            ) : (
              <>
                <h2 className="text-2xl font-semibold text-gray-900 mb-6">
                  {contact.firstName} {contact.lastName}
                </h2>
                <dl className="grid grid-cols-1 gap-6">
                  <div>
                    <dt className="text-sm font-medium text-gray-500">Email</dt>
                    <dd className="mt-1 text-lg text-gray-900">
                      {contact.email}
                    </dd>
                  </div>
                  <div>
                    <dt className="text-sm font-medium text-gray-500">Phone</dt>
                    <dd className="mt-1 text-lg text-gray-900">
                      {contact.phoneNumber}
                    </dd>
                  </div>
                  <div>
                    <dt className="text-sm font-medium text-gray-500">
                      Address
                    </dt>
                    <dd className="mt-1 text-lg text-gray-900">
                      {contact.address}
                    </dd>
                  </div>
                </dl>
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};
