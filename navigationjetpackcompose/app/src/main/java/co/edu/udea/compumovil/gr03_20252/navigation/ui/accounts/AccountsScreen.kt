package co.edu.udea.compumovil.gr03_20252.navigation.ui.accounts

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import co.edu.udea.compumovil.gr03_20252.navigation.R
import co.edu.udea.compumovil.gr03_20252.navigation.data.UserData
import co.edu.udea.compumovil.gr03_20252.navigation.ui.components.AccountRow
import co.edu.udea.compumovil.gr03_20252.navigation.ui.components.StatementBody

/**
 * The Accounts screen.
 */
@Composable
fun AccountsScreen(
    onAccountClick: (String) -> Unit = {},
) {
    val amountsTotal = remember { UserData.accounts.map { account -> account.balance }.sum() }
    StatementBody(
        modifier = Modifier.semantics { contentDescription = "Accounts Screen" },
        items = UserData.accounts,
        amounts = { account -> account.balance },
        colors = { account -> account.color },
        amountsTotal = amountsTotal,
        circleLabel = stringResource(R.string.total),
        rows = { account ->
            AccountRow(
                modifier = Modifier.clickable {
                    onAccountClick(account.name)
                },
                name = account.name,
                number = account.number,
                amount = account.balance,
                color = account.color
            )
        }
    )
}

/**
 * Detail screen for a single account.
 */
@Composable
fun SingleAccountScreen(
    accountType: String? = UserData.accounts.first().name
) {
    val account = remember(accountType) { UserData.getAccount(accountType) }
    StatementBody(
        items = listOf(account),
        colors = { account.color },
        amounts = { account.balance },
        amountsTotal = account.balance,
        circleLabel = account.name,
    ) { row ->
        AccountRow(
            name = row.name,
            number = row.number,
            amount = row.balance,
            color = row.color
        )
    }
}