// Address Book Web Application JavaScript

const API_BASE = '/api';

// Initialize application
document.addEventListener('DOMContentLoaded', () => {
    loadAllContacts();
    updateStats();
});

// API Helper Functions
async function apiGet(endpoint) {
    try {
        const response = await fetch(`${API_BASE}${endpoint}`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('API Error:', error);
        showToast('Failed to connect to server', 'error');
        return null;
    }
}

async function apiPost(endpoint, data) {
    try {
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('API Error:', error);
        showToast('Failed to connect to server', 'error');
        return null;
    }
}

async function apiPut(endpoint, data) {
    try {
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('API Error:', error);
        showToast('Failed to connect to server', 'error');
        return null;
    }
}

async function apiDelete(endpoint) {
    try {
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'DELETE',
        });
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('API Error:', error);
        showToast('Failed to connect to server', 'error');
        return null;
    }
}

// Load all contacts
async function loadAllContacts() {
    const contacts = await apiGet('/contacts');
    if (contacts) {
        displayContacts(contacts);
        document.getElementById('searchStatus').textContent = 'All Contacts';
    }
}

// Search contacts by name
async function searchContacts() {
    const searchInput = document.getElementById('searchInput').value.trim();
    if (!searchInput) {
        showToast('Please enter a name to search', 'info');
        return;
    }

    const contacts = await apiGet(`/search?name=${encodeURIComponent(searchInput)}`);
    if (contacts) {
        displayContacts(contacts);
        document.getElementById('searchStatus').textContent =
            contacts.length > 0 ? `Found ${contacts.length} result(s)` : 'No results found';
        showToast(`Found ${contacts.length} contact(s)`, 'info');
    }
}

// Display contacts in table
function displayContacts(contacts) {
    const tbody = document.getElementById('contactsTableBody');
    const noContactsMessage = document.getElementById('noContactsMessage');

    if (!contacts || contacts.length === 0) {
        tbody.innerHTML = '';
        noContactsMessage.style.display = 'block';
        return;
    }

    noContactsMessage.style.display = 'none';
    tbody.innerHTML = contacts.map(contact => `
        <tr>
            <td><strong>${escapeHtml(contact.contactId)}</strong></td>
            <td>${escapeHtml(contact.name)}</td>
            <td>${escapeHtml(contact.phoneNumber)}</td>
            <td>${escapeHtml(contact.email || '-')}</td>
            <td>${escapeHtml(contact.address || '-')}</td>
            <td class="actions">
                <button class="btn btn-secondary btn-sm" onclick="editContact('${escapeHtml(contact.contactId)}')">Edit</button>
                <button class="btn btn-danger btn-sm" onclick="deleteContact('${escapeHtml(contact.contactId)}')">Delete</button>
            </td>
        </tr>
    `).join('');
}

// Handle form submission
async function handleFormSubmit(event) {
    event.preventDefault();

    const isEditMode = document.getElementById('editMode').value === 'true';
    const contactId = document.getElementById('contactId').value;
    const name = document.getElementById('name').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const email = document.getElementById('email').value.trim();
    const address = document.getElementById('address').value.trim();

    const contactData = {
        contactId: isEditMode ? contactId : generateId(),
        name: name,
        phoneNumber: phone,
        email: email,
        address: address
    };

    let result;
    if (isEditMode) {
        result = await apiPut('/contact', contactData);
    } else {
        result = await apiPost('/contacts', contactData);
    }

    if (result && result.success) {
        showToast(result.message, 'success');
        resetForm();
        loadAllContacts();
        updateStats();
    } else if (result) {
        showToast(result.message, 'error');
    }
}

// Edit contact - populate form
async function editContact(contactId) {
    const contact = await apiGet(`/contact?id=${contactId}`);
    if (contact) {
        document.getElementById('editMode').value = 'true';
        document.getElementById('contactId').value = contact.contactId;
        document.getElementById('name').value = contact.name;
        document.getElementById('phone').value = contact.phoneNumber;
        document.getElementById('email').value = contact.email || '';
        document.getElementById('address').value = contact.address || '';

        document.getElementById('formTitle').textContent = '✏️ Edit Contact';
        document.getElementById('submitBtn').textContent = 'Update Contact';

        // Scroll to form
        document.querySelector('.form-section').scrollIntoView({ behavior: 'smooth' });
    }
}

// Delete contact
async function deleteContact(contactId) {
    if (!confirm('Are you sure you want to delete this contact?')) {
        return;
    }

    const result = await apiDelete(`/contacts?id=${contactId}`);
    if (result && result.success) {
        showToast(result.message, 'success');
        loadAllContacts();
        updateStats();
    } else if (result) {
        showToast(result.message, 'error');
    }
}

// Reset form
function resetForm() {
    document.getElementById('contactForm').reset();
    document.getElementById('editMode').value = 'false';
    document.getElementById('contactId').value = '';
    document.getElementById('formTitle').textContent = '➕ Add New Contact';
    document.getElementById('submitBtn').textContent = 'Add Contact';
}

// Update stats
async function updateStats() {
    const stats = await apiGet('/stats');
    if (stats) {
        document.getElementById('totalContacts').textContent = stats.totalContacts;
    }
}

// Generate unique ID
function generateId() {
    const now = new Date();
    const timestamp = now.getTime().toString(36).toUpperCase();
    const random = Math.random().toString(36).substring(2, 5).toUpperCase();
    return 'C' + timestamp + '-' + random;
}

// Escape HTML to prevent XSS
function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Show toast notification
function showToast(message, type = 'info') {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = 'toast ' + type;
    toast.classList.add('show');

    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

// Enter key search
document.getElementById('searchInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        searchContacts();
    }
});

