package entity;

import service.BalanceSheet;
import service.impl.BalanceSheetImpl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Group {
    private String groupName;
    private String groupDescription;
    private final Set<User> members;
    private final List<Expense> expenses;
    private final BalanceSheet balanceSheet;

    public Group(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.members = new CopyOnWriteArraySet<>();
        this.expenses = new CopyOnWriteArrayList<>();
        this.balanceSheet = new BalanceSheetImpl();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        if (user != null) {
            members.add(user);
        }
    }

    public void removeMember(User user) {
        if (user != null) {
            members.remove(user);
        }
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        if (expense != null) {
            expenses.add(expense);
            balanceSheet.addExpense(this,expense);
        }
    }

    public void removeExpense(Expense expense) {
        if (expense != null) {
            expenses.remove(expense);
            // handle balance sheet update if necessary
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", members=" + members +
                '}';
    }
}
